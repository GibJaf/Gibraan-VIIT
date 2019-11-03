# Just put below statements in python shell to create chatbot

from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer

english_bot = ChatBot("Chatterbot", storage_adapter="chatterbot.storage.SQLStorageAdapter")
trainer = ChatterBotCorpusTrainer(english_bot)
trainer.train("chatterbot.corpus.english")

# To give input => english_bot.get_response("stuff to ask")
# Will get response as => <Statement text: <response_text>>
