package com.techmania.navigationexample

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {

    val userName = remember {
        mutableStateOf("")
    }
    val userAge = remember {
        mutableStateOf("")
    }

    val myContext = LocalContext.current

    Scaffold(

        topBar = {
            TopAppBar(

                title = { Text(text = "Main Page", color = Color.White, fontSize = 20.sp) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500)
                )

            )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = userName.value,
                    onValueChange = {userName.value = it},
                    label = { Text(text = "Enter your name")},
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = colorResource(id = R.color.purple_500)
                    ),
                    modifier = Modifier.size(300.dp,60.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                    shape = RoundedCornerShape(5.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = userAge.value,
                    onValueChange = {userAge.value = it},
                    label = { Text(text = "Enter your age")},
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = colorResource(id = R.color.purple_500)
                    ),
                    modifier = Modifier.size(300.dp,60.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                    shape = RoundedCornerShape(5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = {

                        if (userName.value.isEmpty() || userAge.value.isEmpty()){

                            Toast.makeText(myContext,"Please enter all data",Toast.LENGTH_SHORT).show()

                        }else{

                            try {
                                navController.navigate("SecondPage/${userName.value}/${userAge.value}"){
                                    //popUpTo("MainPage"){inclusive = true} -> removes the MainPage from back-stack
                                }
                            }catch (e : IllegalArgumentException){
                                Toast.makeText(myContext,"Please enter valid data",Toast.LENGTH_SHORT).show()
                            }



                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.size(200.dp,60.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.purple_500))
                ) {
                    Text(text = "Send", color = colorResource(id = R.color.purple_500), fontSize = 24.sp)
                }

            }
        }

    )

}