package com.baskingcat.game

import org.lwjgl._
import java.io._

object Message {

  def error(e: Throwable, message: String) = {
    Sys.alert("Error", message)
    throw e
  }

  def systemError(e: Throwable) = error(e, "システムエラー")

  def fileNotFoundError(e: Throwable, fileName: String) = error(e, fileName + "が見つかりません。")

}
