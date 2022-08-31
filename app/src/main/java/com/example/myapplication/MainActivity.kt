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
import com.example.myapplication.database.NODE_EVENT
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
            //recordToDBEvent()
        }
    }

    private fun recordToDBEvent() {
        val event = Event (
            "2022-09-03",
            "ПРОХВАТ «Дорога В Мотомир»",
            "Россия, Санкт-Петербург, территория Малая Охта",
            "Вас приветствует команда SNAKE WAY!\nПриглашаем всех первосезонников и выпускников мотошкол на наш тренировочный прохват!\nМы проведем вас по незабываемым дорогам и интересным достопримечательностям. Научим правильно ездить в колонне и междурядье, в городском трафике и трассе. Познакомим с мотомиром, подарим компанию и друзей.\nСпециально для тех, кто недавно получил категорию «А» и оседлал железного коня, мы создали телеграм-канал и чат. Там вы сможете получать информацию о наших закрытых мероприятиях, а также общаться друг с другом.\nРазыграем 2 мультисертификата среди первосезонников на занятие по повышению навыков управления мотоциклом.\nПеред выездом рекомендуем плотно поесть.\nНа одной из локаций мы приготовим фирменный кофе от Snake Way и устроим перекус.\nМероприятие подготовлено специально для первосезонников и неопытных мотоциклистов, поедем строго колонной в шахматном порядке.\nЕсли будет сильная жара, то обязательно заедем на озеро искупаться, не забудьте взять купальные принадлежности.\nМы ждем тебя,так что не стесняйся, а присоединяйся!\nОбещаем, все пройдет душевно, весело, да по-доброму!\nВремя сбора в 13:00.",
            "https://img.spb.systems/new/events/300/54/7354.jpg"
                )
        REMOTE_DATABASE.child(NODE_EVENT).child("date").child(event.date).setValue(event)
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
