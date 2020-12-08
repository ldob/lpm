import { Injectable } from '@angular/core';
import {formatDate} from "@angular/common";

export enum LogLevel {
  Debug = 1,
  Info = 2,
  Warn = 3,
  Error = 4,
  Fatal = 5,
  Off = 0
}

@Injectable({
  providedIn: 'root'
})
export class LogService {

  currentLogLevel = LogLevel.Debug;

  constructor() { }

  debug(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Debug, optionalParams);
  }

  info(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Info, optionalParams);
  }

  warn(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Warn, optionalParams);
  }

  error(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Error, optionalParams);
  }

  fatal(msg: string, ...optionalParams: any[]) {
    this.writeToLog(msg, LogLevel.Fatal, optionalParams);
  }

  writeToLog(msg: string, level: LogLevel, ...optionalParams: any[]) {
    if(level >= this.currentLogLevel) {
      console.log(formatDate(new Date(), "y-MM-dd HH:mm:ss.SSS", "de-AT") + ": " + msg);
      optionalParams.forEach(function(param) {
        console.log(typeof(param) + " --> " + JSON.stringify(param));
      });
    }

  }
}
