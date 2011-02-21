package baskingcat.game

import java.io.{ File, FileReader }
import java.util.Properties
import scala.collection.JavaConversions._

abstract class AbstractGame {

  protected val config: String

  protected lazy val controller = {
    val properties = new Properties
    properties.load(getClass.getClassLoader.getResource(config).openStream)
    def createMap(key: String, default: Seq[Int]) = properties.getProperty(key, default.mkString(",")).split(",") map (_.toInt)
    val keys = {
      import org.lwjgl.input.Keyboard._
      createMap("keys", List(KEY_Z, KEY_X, KEY_C, KEY_V))
    }
    val buttons = createMap("buttons", 0 until 8)
    new GameController(keys, buttons)
  }

  protected val scene: Scene

  protected def init()

  protected def run(controller: GameController, scene: Scene)

  protected def cleanup()

  def main(args: Array[String]) {
    val success = 0
    val error = 1
    val result = try {
      init()
      run(controller, scene)
      success
    } catch {
      case e => e.printStackTrace();error
    }
    finally {
      cleanup()
    }
    exit(result)
  }

}

