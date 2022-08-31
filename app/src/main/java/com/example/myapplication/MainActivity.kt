package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.NODE_NEWS
import com.example.myapplication.database.REMOTE_DATABASE
import com.example.myapplication.database.initFirebase
import com.example.myapplication.models.Event
import com.example.myapplication.ui.navigation.RootNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

lateinit var ACTIVITY_CONTEXT: ComponentActivity

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ACTIVITY_CONTEXT = this
            initFirebase()
            App()
            //Служебная функция для записи событий в базу данных
            recordToDBEvent()
        }
    }

    private fun recordToDBEvent() {
        val event = Event (
            "2022-08-28",
            "Royal Enfield начал выпуск Hunter 350",
            "Классический родстер в индийском исполнении будет производиться в двух версиях: Retro и Metro",
            "Причём, в данном случае можно смело говорить не о ретро-стилистике, а о настоящем ретро-мотоцикле, производимом в наши дни. Royal Enfield Hunter 350 оснащается 350-кубовым одноцилиндровым двигателем, знакомым по моделям Meteor и Classic 350. Оснащённый системой впрыска топлива, мотор развивает скромные 20 л.с. при 6100 об/мин, пик крутящего момента 25,6 Нм приходится на 4000 об/мин.\nHunter 350 выпускается в двух вариантах исполнения: Retro или Metro. Версия Retro выделяется спицованными 17-дюймовыми колёсами, в то время как Metro оснащается легкосплавными дисками такой же размерности. Больше глобальных различий между версиями нет, отличаются только цвето-графические схемы.\nА знаете, сколько стоит Royal Enfield Hunter 350 в Индии? Версия Retro в расцветке Factory Black или Factory Silver обойдётся в 149 900 рупий, что по сегодняшнему курсу эквивалентно 113 000 рублей. Исполнение Metro, доступное в цветах Dapper White, Dapper Ash и Dapper Grey, будет стоить чуть дороже, от 163 900 рупий, т.е. около 124 000 рублей. Самыми же дорогими будут Hunter 350 Metro в расцветках Rebel Black, Rebel Blue и Rebel Red — цена на них будет 168 900 рупий, т.е. примерно 127 000 рублей.",
            "https://moto-magazine.ru/news/2022/08/2023-royal-enfield-hunter-350---rebel-blue.jpg"
                )
        REMOTE_DATABASE.child(NODE_NEWS).child("date").setValue(event.date)
        REMOTE_DATABASE.child(NODE_NEWS).child("date").child(event.date).setValue(event)
    }
}

@Composable
fun App() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            RootNavGraph(navController = rememberNavController())
        }
    }
}
