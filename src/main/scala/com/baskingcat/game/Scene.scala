package com.baskingcat.game

abstract class Scene {

  def logic(controller: GameController): Scene

  def render()

  def dispose()

}
