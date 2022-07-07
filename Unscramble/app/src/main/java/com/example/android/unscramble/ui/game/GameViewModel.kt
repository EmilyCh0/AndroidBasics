package com.example.android.unscramble.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameViewModel: ViewModel() {

    private var _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score
    private var _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int> get() = _currentWordCount
    private val _currentScrambleWord = MutableLiveData<String>()
    val currentScrambleWord: LiveData<String> get() = _currentScrambleWord

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
            _currentScrambleWord.value = String(shuffled)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
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
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore(){
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean{
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

}