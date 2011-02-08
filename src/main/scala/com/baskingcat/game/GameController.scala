
package com.baskingcat.game

import xml.{Group => _, _}
import org.lwjgl.input._
import org.lwjgl.util.input._

final class GameController(keyMap: Map[Int, Int], buttonMap: Map[Int, Int]) {

  {
    Keyboard.create()
    Controllers.create()
  }

  private lazy val controller = Controllers.getController(0)

  def buttonPressed(id: Int) = try {
    controller.isButtonPressed(buttonMap(id))
  } catch {
    case e => Message.systemError(e)
  }

  def xAxis = controller.getXAxisValue()

  def yAxis = controller.getYAxisValue()

  def next = Controllers.next

  def destroy() {
    Controllers.destroy()
    Keyboard.destroy()
  }

  def poll() {
    Controllers.poll()
    Keyboard.poll()
  }

}








