package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameViewModel: ViewModel() {
    private var _score = 0
    val score: Int get() = _score
    private var _currentWordCount = 0
    val currentWordCount: Int get() = _currentWordCount
    private lateinit var _currentScrambleWord:String
    val currentScrambleWord: String get() = _currentScrambleWord

    private val wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val shuffled = currentWord.toCharArray()
        shuffled.shuffle()
        while(String(shuffled).equals(currentWord,false)){
            shuffled.shuffle()
        }
        if(wordsList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambleWord = String(shuffled)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    init {
        getNextWord()
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean{
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }

}