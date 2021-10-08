package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * 이 액티비티는 사용자가 주사위를 굴려 그 결과를 화면에 볼 수 있도록 한다.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * 주사위를 굴리고 그 결과를 화면에 업데이트한다.
     */
    private fun rollDice() {
        // 6면 주사위를 생성하고 주사위를 굴린다
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceRoll2 = dice.roll()
        // imageView 를 찾는다
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.secondDiceIV)
        // 주사위를 굴린 결과에 해당하는 drawable resource 를 찾는다
        val drawableResource = when(diceRoll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource2 = when(diceRoll2){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update ImageView
        diceImage.setImageResource(drawableResource)
        diceImage2.setImageResource(drawableResource2)
        // Update content description
        diceImage.contentDescription = diceRoll.toString()
        diceImage2.contentDescription = diceRoll2.toString()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}