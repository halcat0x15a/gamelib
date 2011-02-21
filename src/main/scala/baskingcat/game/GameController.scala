package baskingcat.game

import xml.{Group => _, _}
import org.lwjgl.input._
import org.lwjgl.util.input._

final class GameController(keys: Seq[Int], buttons: Seq[Int]) {

  {
    Keyboard.create()
    Controllers.create()
  }

  private lazy val controller = try {
    Some(Controllers.getController(0))
  } catch {
    case e => None
  }

  def buttonPressed(id: Int) = try {
    controller match {
      case Some(controller) => controller.isButtonPressed(buttons(id))
      case None => Keyboard.isKeyDown(keys(id))
    }
  } catch {
    case e => Message.systemError(e)
  }

  def xAxis = controller match {
    case Some(controller) => controller.getXAxisValue()
    case None => if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
      -1
    } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
      1
    } else {
      0
    }
  }

  def yAxis = controller match {
    case Some(controller) => controller.getYAxisValue()
    case None => if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      -1
    } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      1
    } else {
      0
    }
  }

  def next = if (controller.isDefined)
    Controllers.next
  else
    Keyboard.next

  def destroy() {
    Controllers.destroy()
    Keyboard.destroy()
  }

  def poll() {
    Controllers.poll()
    Keyboard.poll()
  }

}








