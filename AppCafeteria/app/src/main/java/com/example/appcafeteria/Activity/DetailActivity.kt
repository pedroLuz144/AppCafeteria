package com.example.appcafeteria.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appcafeteria.Domain.ItemsModel
import com.example.appcafeteria.R
import com.example.appcafeteria.databinding.ActivityDetailBinding
import com.example.appcafeteria.databinding.ViewholderPopularBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart:ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        binding.apply{
            botaoPequeno.setOnClickListener {
                botaoPequeno.setBackgroundResource(R.drawable.stroke_brown_bg)
                botaoMedio.setBackgroundResource(0)
                botaoGrande.setBackgroundResource(0)
            }
            botaoMedio.setOnClickListener {
                botaoPequeno.setBackgroundResource(0)
                botaoMedio.setBackgroundResource(R.drawable.stroke_brown_bg)
                botaoGrande.setBackgroundResource(0)
            }
            botaoGrande.setOnClickListener {
                botaoPequeno.setBackgroundResource(0)
                botaoMedio.setBackgroundResource(0)
                botaoGrande.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply{
            item= intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text=item.title
            DescricaoTxt.text=item.description
            priceTxt.text="R$"+item.price
            avaliacaoTxt.text=item.rating.toString()

            adicionarAoCarrinhoBtn.setOnClickListener{
                item.numberInCart=Integer.valueOf(
                    numberItemTxt.text.toString()
                )
                managmentCart.insertItems(item)
            }

            botaoVoltar.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }

            adicionarCarrinho.setOnClickListener {
                numberItemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            botaoRemover.setOnClickListener {
                if(item.numberInCart>0){
                    numberItemTxt.text=(item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}