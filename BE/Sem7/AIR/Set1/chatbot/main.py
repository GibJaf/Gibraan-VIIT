from chatterbot import ChatBot
from chatterbot.trainers import ListTrainer
from tkinter import *

bot = ChatBot("my bot")

convo=[
    'how does the investment work?',
    'by investing your money',
    'what are your goals?',
    'looking for safety income',
    'how long do you  plan to invest ?',
    'near about 3 years..'
    'what you have invested already?'


]

trainer = ListTrainer(bot)

trainer.train(convo)

#answer=bot.get_response("what is cost of apple?")
#print(answer)
#print("talk to bot ")
#while True:
#    query=input()
#    if query=='exit':
#        break
#    answer =bot.get_response(query)
#    print("bot: ",answer)

main = Tk()
main.geometry("500x650")
main.title("chat bot")
def ask_from_bot():
    query = textF.get()
    answer_from_bot = bot.get_response(query)
    msgs.insert(END, "you : " + query)
    print(type(answer_from_bot))
    msgs.insert(END, "bot : " + str(answer_from_bot))
    textF.delete(0,END)

frame = Frame(main)
sc = Scrollbar(frame)
msgs=Listbox(frame, width=80, height=20)
sc.pack(side=RIGHT , fill=Y)
msgs.pack(side=LEFT, fill=BOTH, pady=10)
frame.pack()

textF = Entry(main, font=("verdana",20))
textF.pack(fill=X , pady=10)

btn = Button(main, text="Ask from bot", font=("Verdana", 20), command=ask_from_bot )
btn.pack()

main.mainloop()