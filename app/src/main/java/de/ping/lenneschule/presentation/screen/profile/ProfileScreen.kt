package de.ping.lenneschule.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.ping.lenneschule.R

@Composable
fun ProfileScreen(
    onButtonClick: () -> Unit,
) {

    Column {
        Text(text = stringResource(id = R.string.profile))

        Button(onClick = onButtonClick) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}