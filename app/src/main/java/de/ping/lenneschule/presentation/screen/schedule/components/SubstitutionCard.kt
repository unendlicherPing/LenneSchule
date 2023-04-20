package de.ping.lenneschule.presentation.screen.schedule.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.ping.lenneschule.domain.model.Subject
import de.ping.lenneschule.domain.model.Substitution
import de.ping.lenneschule.domain.model.Teacher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubstitutionCard(
    substitution: Substitution, modifier: Modifier = Modifier
) {

    var isExpanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (isExpanded) 270f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = MaterialTheme.shapes.medium,
        onClick = { isExpanded = !isExpanded },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(12.dp)) {
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Class ${substitution.`class`}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Block ${substitution.block}",
                    style = MaterialTheme.typography.headlineMedium,
                )

            }
            if (isExpanded) {
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        if (substitution.subject.name.isNotEmpty() && substitution.substitutionSubject.name.isNotEmpty())
                            Text(
                                text = substitution.subject.name,
                            )

                        if (substitution.teacher.name.isNotEmpty() && substitution.substitutionTeacher.name.isNotEmpty())
                            Text(
                                text = substitution.teacher.name,
                            )

                        if (substitution.room.isNotEmpty() && substitution.substitutionRoom.isNotEmpty())
                            Text(
                                text = substitution.room,
                            )
                    }

                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .rotate(rotationState),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                    )

                    Column(horizontalAlignment = Alignment.End) {
                        if (substitution.subject.name.isNotEmpty() && substitution.substitutionSubject.name.isNotEmpty())
                            Text(
                                text = substitution.substitutionSubject.name,
                            )

                        if (substitution.teacher.name.isNotEmpty() && substitution.substitutionTeacher.name.isNotEmpty())
                            Text(
                                text = substitution.substitutionTeacher.name,
                            )

                        if (substitution.room.isNotEmpty() && substitution.substitutionRoom.isNotEmpty())
                            Text(
                                text = substitution.substitutionRoom,
                            )
                    }
                }

                if (substitution.hints.isNotEmpty()) {
                    Divider()
                    Text(text = "Hint", style = MaterialTheme.typography.headlineSmall)
                    Text(text = substitution.hints)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubstitutionCardPreview() {

    SubstitutionCard(
        Substitution(
            block = "2",
            `class` = "10/2",
            date = "2023-03-28",
            hints = "Aufgaben in der cloud",
            important = "richtig wichtig",
            room = "37/4",
            substitutionRoom = "306",
            teacher = Teacher(
                name = "Florian Selbiger",
                forename = "Florian",
                surname = "Selbiger",
                token = "F. Selbiger",
            ),
            substitutionTeacher = Teacher(
                name = "Anke Kreuzberger",
                forename = "Anke",
                surname = "Kreuzberger",
                token = "A. Kreuzberger"
            ),
            subject = Subject(name = "English", token = "En"),
            substitutionSubject = Subject(name = "German", token = "De"),
        )
    )
}