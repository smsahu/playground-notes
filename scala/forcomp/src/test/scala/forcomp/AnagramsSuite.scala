package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }

  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }
  
  test("sentenceOccurrences: abcd e e") {
    assert(sentenceOccurrences(List("abcd", "e", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 2)))
  }

  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }

  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }
  
  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }  
  
  test("simple combinations") {
    assert(combinations(List(('k', 1), ('o', 1))).toSet === List(List(), List(('k', 1)), List(('o', 1)), List(('k', 1), ('o', 1))).toSet)
  }
 
  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }  
  
  test("combinations: kot") {
    val kot = List(('k', 1), ('o', 1), ('t', 1))
    val kotcomb = List(
      List(),
      List(('k', 1)),
      List(('o', 1)),
      List(('t', 1)),
      List(('k', 1), ('o', 1)),
      List(('k', 1), ('t', 1)),
      List(('o', 1), ('t', 1)),
      List(('k', 1), ('o', 1), ('t', 1))
    )
    assert(combinations(kot).toSet === kotcomb.toSet)
  }  

  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }
  
  test("subtract: lard - rd") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val rd = List(('r', 1), ('d', 1))
    val la = List(('a', 1), ('l', 1))
    assert(subtract(lard, rd) === la)
  }

  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }
  
  test("sentence anagrams: Eat") {
    val sentence = List("Eat")
    val anas = List(
      List("eat"),
      List("tea"),
      List("ate")      
    )

    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }    

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }  
}
