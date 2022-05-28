package main

import (
	"bufio"
	"encoding/json"
	"flag"
	"fmt"
	"log"
	"math/rand"
	"os"
	"path"
	"strconv"
	"time"
)

var (
	num     int
	logFile string
	f       *os.File
)

var events = []string{"visit", "start", "register", "login", "pay"}

var devices = []string{"IOS", "Android", "H5"}

func main() {
	flag.IntVar(&num, "num", 100, "number of logs")
	flag.StringVar(&logFile, "logFile", "./log/messages/zag13.log", "file of log")
	flag.Parse()

	_, err := os.Stat(logFile)
	if err != nil && os.IsNotExist(err) {
		_ = os.MkdirAll(path.Dir(logFile), 0750)
		f, err = os.Create(logFile)
	} else {
		f, err = os.OpenFile(logFile, os.O_WRONLY|os.O_APPEND, 0666)
	}

	if err != nil {
		log.Fatalln(err)
	}

	defer f.Close()

	write := bufio.NewWriter(f)
	for i := 0; i < num; i++ {
		data := map[string]string{
			"userId":     randStr(32),
			"action":     events[rand.Intn(len(events))],
			"deviceType": devices[rand.Intn(len(devices))],
			"eventTime":  time.Now().Format("2006-01-02 15:04:05"),
		}
		jsonString, _ := json.Marshal(data)
		write.WriteString(string(jsonString) + "\n")
	}

	write.Flush()

	fmt.Println("已生成日志" + strconv.Itoa(num) + "条")
}

const letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

func randStr(n int) string {
	b := make([]byte, n)
	for i := range b {
		b[i] = letters[rand.Int63()%int64(len(letters))]
	}
	return string(b)
}
