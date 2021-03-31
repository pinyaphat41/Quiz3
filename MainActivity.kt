package com.example.q

import android.R
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : Activity(), View.OnClickListener {
    var begin: Button? = null
    var answer1: Button? = null
    var answer2: Button? = null
    var answer3: Button? = null
    var answer4: Button? = null
    var Buttons = arrayOf(begin, answer1, answer2, answer3, answer4)
    var display_1: TextView? = null
    var question: TextView? = null
    var Sound_click: MediaPlayer? = null
    private var a1: String? = null
    private var a2: String? = null
    private var a3: String? = null
    private var a4 = ""
    var QuestionIds: MutableList<Int?> = ArrayList()
    var quiz = arrayOf(
            "คำถามข้อที่ 1",  //1
            "คำถามข้อที่  2",  //2
            "คำถามข้อที่  3")  //3
            "คำถามข้อที่  4",  //4
            "คำถามข้อที่ 5", //5
    private val correctList = arrayOf(
            "คำตอบที่ถูก ข้อที่ 1",  //1
            "คำตอบที่ถูก ข้อที่  2",  //2
            "คำตอบที่ถูก ข้อที่  3")  //3
            "คำตอบที่ถูก ข้อที่  4",  //4
            "คำตอบที่ถูก ข้อที่  5", //5

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data
        begin!!.setOnClickListener(this)
        answer4!!.setOnClickListener(this)
        answer1!!.setOnClickListener(this)
        answer2!!.setOnClickListener(this)
        answer3!!.setOnClickListener(this)
    }

    // Gets the data
    // QuestionIds.get(0);
    // QuestionIds.remove(0);
    // answer1.setVisibility(View.INVISIBLE);
    // answer2.setVisibility(View.INVISIBLE);
    // answer3.setVisibility(View.INVISIBLE);
    // answer4.setVisibility(View.INVISIBLE);
    private val data: Unit
        private get() {
            // Gets the data
            begin = findViewById<View>(R.id.btnStart) as Button
            answer1 = findViewById<View>(R.id.button1) as Button
            answer2 = findViewById<View>(R.id.button2) as Button
            answer3 = findViewById<View>(R.id.button3) as Button
            answer4 = findViewById<View>(R.id.button4) as Button
            question = findViewById<View>(R.id.question) as TextView
            display_1 = findViewById<View>(R.id.answ1) as TextView
            for (b in quiz.indices) {
                QuestionIds.add(b)
            }
            Collections.shuffle(QuestionIds)
            // QuestionIds.get(0);
            // QuestionIds.remove(0);
            begin!!.text = "เริ่มสอบ"
            answer4!!.text = ""
            answer3!!.text = ""
            answer2!!.text = ""
            answer1!!.text = ""
            // answer1.setVisibility(View.INVISIBLE);
            // answer2.setVisibility(View.INVISIBLE);
            // answer3.setVisibility(View.INVISIBLE);
            // answer4.setVisibility(View.INVISIBLE);
        }

    fun onPause(b: Bundle?) {
        super.onCreate(b)
    }

    private var correct = 0
    override fun onClick(view: View) {
        Sound_click = MediaPlayer.create(this, R.raw.click)
        if (finished || counter > quiz.size + 1) {
            display_1!!.text = results(correct, quiz.size)
            return
        }
        when (view.id) {
            R.id.btnStart -> {
            }
            R.id.button1 -> if (isCorrect(1)) correct++
            R.id.button2 -> if (isCorrect(2)) correct++
            R.id.button3 -> if (isCorrect(3)) correct++
            R.id.button4 -> if (isCorrect(4)) correct++
        }
        Sound_click!!.start()
        QBegin()
    }

    fun isCorrect(button: Int): Boolean {
        if (finished) return false
        for (i in correctList.indices) {
            if (a1 === correctList[i] && button == 1 || a2 === correctList[i] && button == 2 || a3 === correctList[i] && button == 3 || a4 === correctList[i] && button == 4) return true
        }
        return false
    }

    private val finished = false
    private var counter = 0
    var indexes: List<Int> = ArrayList()
    private fun results(a: Int, x: Int): String {
        val mark = 5 * a / x
        return "คะแนนของคุณคือ$mark"
    }

    private fun QBegin() {
        for (index in QuestionIds) {
            question!!.text = quiz.get(index)
            getAnswers(index)
            //QuestionIds.remove(index);
        }
    }

    private fun getAnswers(Type: Int) {
        try {
            val answers_list = arrayOf(arrayOf(correctList[Type], "คำตอบผิดของข้อ 1", "คำตอบผิดของข้อ 1", "คำตอบผิดของข้อ 1"), arrayOf(correctList[Type], "คำตอบผิดของข้อ 2", "คำตอบผิดของข้อ 2", "คำตอบผิดของข้อ 2"), arrayOf(correctList[Type], "คำตอบผิดของข้อ 3", "คำตอบผิดของข้อ 3", "คำตอบผิดของข้อ 3"), arrayOf(correctList[Type], "คำตอบผิดของข้อ 4", "คำตอบผิดของข้อ 4", "คำตอบผิดของข้อ 4"), arrayOf(correctList[Type], "คำตอบผิดของข้อ 5", "คำตอบผิดของข้อ 5", "คำตอบผิดของข้อ 5"))
            Collections.shuffle(Arrays.asList(*answers_list[Type]))
            answer1!!.visibility = View.VISIBLE
            answer2!!.visibility = View.VISIBLE
            answer3!!.visibility = View.VISIBLE
            answer4!!.visibility = View.VISIBLE
            answer1!!.text = answers_list[Type][0]
            answer2!!.text = answers_list[Type][1]
            answer3!!.text = answers_list[Type][2]
            answer4!!.text = answers_list[Type][3]
            a1 = answers_list[Type][0]
            a2 = answers_list[Type][1]
            a3 = answers_list[Type][2]
            a4 = answers_list[Type][3]
            counter++
        } catch (ex: Exception) {
            println(ex)
        }
    }

    class Question(val question: String, private val correctAnswer: String,
                   incorrectAnswers: List<String?>?) {
        private val incorrectAnswers: List<String?>
        val possibleAnswers: List<String?>
            get() {
                val result: MutableList<String?> = ArrayList()
                result.addAll(incorrectAnswers)
                result.add(correctAnswer)
                Collections.shuffle(result)
                return result
            }

        fun isCorrectAnswer(answer: String): Boolean {
            return if (answer == correctAnswer) {
                true
            } else false
        }

        init {
            // TODO: empty strings/nulls checks
            this.incorrectAnswers = ArrayList(incorrectAnswers)
        }
    }

    companion object {
        fun random(range: Int): Int {
            return (Math.random() * (range + 1)).toInt()
        }
    }
}