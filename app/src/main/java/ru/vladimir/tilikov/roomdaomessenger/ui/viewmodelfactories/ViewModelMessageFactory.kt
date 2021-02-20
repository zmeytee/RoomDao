/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.vladimir.tilikov.roomdaomessenger.ui.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.vladimir.tilikov.roomdaomessenger.data.db.MessageDao
import ru.vladimir.tilikov.roomdaomessenger.ui.messages.MessagesFragmentViewModel

class ViewModelMessageFactory(private val contactId: Long, private val messageDao: MessageDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessagesFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MessagesFragmentViewModel(contactId, messageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
