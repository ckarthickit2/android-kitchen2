package me.kartdroid.androidkitchen2.dragablecompose

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import me.kartdroid.androidkitchen2.R

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 24/11/23
 */
class DragableComposeActivity : FragmentActivity() {

    private lateinit var addDraggableButton: Button
    private lateinit var removeDraggableButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draggable_compose2)
        initViews()
        setupListeners()
    }

    private fun initViews() {
        addDraggableButton = findViewById(R.id.addDraggable)
        removeDraggableButton = findViewById(R.id.removeDraggable)
    }

    private fun setupListeners() {
        addDraggableButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.draggable_compose_container, DraggableComposeFragment(), "DraggableComposeFrag")
                    .commit()
        }

        removeDraggableButton.setOnClickListener {
            val frag = supportFragmentManager.findFragmentByTag("DraggableComposeFrag")
            frag?.let {
                supportFragmentManager.beginTransaction().remove(it).commit()
            }

        }
    }
}