package xyz.webflutter.kadefootballlanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private val itemFootball: MutableList<ItemFootball> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(16)
            recyclerView {
                lparams {
                    width = matchParent
                    height = matchParent
                }
                layoutManager = GridLayoutManager(context, 2)
                adapter = AdapterFootball(itemFootball) {
                    val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
                    toast.show()
                    startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it)
                }
            }
        }
        initData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_language -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.id)
        val name = resources.getStringArray(R.array.name)
        val desc = resources.getStringArray(R.array.desc)
        val logo = resources.obtainTypedArray(R.array.logo)

        itemFootball.clear()
        for (i in name.indices) {
            itemFootball.add(ItemFootball(id[i], name[i], desc[i], logo.getResourceId(i, 0)))
        }
        logo.recycle()
    }
}
