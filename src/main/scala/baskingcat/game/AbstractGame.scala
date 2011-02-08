package baskingcat.game

abstract class AbstractGame {

  protected val controller: GameController

  protected val scene: Scene

  protected def init()

  protected def run(controller: GameController, scene: Scene)

  protected def cleanup()

  def main(args: Array[String]) {
    try {
      init()
      run(controller, scene)
    } catch {
      case e => {
	e.printStackTrace()
      }
    }
    finally {
      cleanup()
    }
  }

}

