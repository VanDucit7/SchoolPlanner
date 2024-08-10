package screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import schoolplanner.composeapp.generated.resources.Res
import schoolplanner.composeapp.generated.resources.configure_later
import schoolplanner.composeapp.generated.resources.configure_later_des
import schoolplanner.composeapp.generated.resources.configure_now
import schoolplanner.composeapp.generated.resources.configure_now_des
import schoolplanner.composeapp.generated.resources.ic_book_saved
import schoolplanner.composeapp.generated.resources.let_started

@Composable
fun ConfigurationScreen(
    onConfigNowClick: () -> Unit,
    onConfigLateClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_book_saved),
            contentDescription = "App Icon",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.let_started),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(Res.string.configure_now_des),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onConfigNowClick
        ) {
            Text(text = stringResource(Res.string.configure_now))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.configure_later_des),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onConfigLateClick
        ) {
            Text(text = stringResource(Res.string.configure_later))
        }
    }
}