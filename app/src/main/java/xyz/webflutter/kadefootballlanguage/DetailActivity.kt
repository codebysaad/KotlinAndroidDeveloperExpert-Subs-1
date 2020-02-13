package xyz.webflutter.kadefootballlanguage

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.support.v4.nestedScrollView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getParcelableExtra<ItemFootball>(EXTRA_ID)
        DetailActivityUI(data).setContentView(this)
        initialActionBar(data)
    }

    private fun initialActionBar(itemFootball: ItemFootball) {
        supportActionBar?.apply {
            title = itemFootball.name
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    class DetailActivityUI(private val itemFootball: ItemFootball) : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
            nestedScrollView {
                cardView {
                    cardElevation = dip(2).toFloat()
                    radius = dip(5).toFloat()
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    ).apply { margin = dip(16) }

                    verticalLayout {
                        orientation = LinearLayout.VERTICAL
                        backgroundResource = attr(R.attr.selectableItemBackground).resourceId
                        isClickable = true

                        imageView {
                            Picasso.get()
                                .load(itemFootball.logo)
                                .placeholder(R.drawable.ic_broken_image)
                                .error(R.drawable.ic_broken_image)
                                .into(this)
                        }.lparams(500, 500) {
                            topMargin = dip(16)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        textView {
                            gravity = Gravity.CENTER
                            textSize = 30f
                            allCaps = true
                            textColor = Color.BLACK
                            setTypeface(typeface, Typeface.BOLD)
                            text = itemFootball.name

                        }.lparams(matchParent, wrapContent) {
                            gravity = Gravity.CENTER_HORIZONTAL
                            margin = dip(10)
                            topMargin = dip(16)
                        }

                        textView {
                            text = itemFootball.description
                            padding = dip(5)
                            textSize = 18f
                            typeface = Typeface.MONOSPACE
                            gravity = Gravity.START
                        }.lparams(matchParent, wrapContent) {
                            marginStart = dip(10)
                            marginEnd = dip(10)
                            topMargin = dip(16)
                            bottomMargin = dip(10)
                        }
                    }
                }
            }
        }
    }
}
