package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import schoolplanner.composeapp.generated.resources.Res
import schoolplanner.composeapp.generated.resources.ic_document_normal
import ui.model.TermModel

@Composable
fun TermItemView(
    term: TermModel,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(resource = Res.drawable.ic_document_normal),
            contentDescription = "Term Icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = term.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "${term.startDate} - ${term.endDate}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}