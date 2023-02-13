import pyautogui
import copy
import tkinter as tk
import tkinter.filedialog as fd


def check_a(a):
    while True:
        if a % 4 == 1:
            return a
        else:
            a += 1


def check_b(b):
    while True:
        if b % 2 == 1:
            return b
        else:
            b += 1


def NOD(m, n):
    while n > 0:
        m, n = n, m % n
    if m == 1:
        return True
    return False


def generate_random_number(number):
    number = int((number * a + b)) % m
    return number

def k_number(len_seque, number):
    list_number=[]
    for i in range(len_seque):
        number = generate_random_number(number)
        tmp=[number]
        list_number.append(tmp)
    return list_number


def distribution():
    amoun_of_numbers = m - 1
    interval = amoun_of_numbers / 100
    list_number = k_number(1000,number0)


    for i in range(1000):
        list_number[i].append(100 * list_number[i][0] / amoun_of_numbers)


    for i in range(1000):
        for j in range(1000):
            if list_number[i][0] >= (j * interval) and list_number[i][0] <= (j + 1) * interval:
                list_number[i].append(j + 1)

    list_aver = []
    for i in range(1000):
        tmp = [0, 0]
        list_aver.append(copy.deepcopy(tmp))


    for i in range(1000):
        for j in range(1000):
            if list_number[i][2] == j + 1:
                list_aver[j][0] += list_number[i][1]
                list_aver[j][1] += 1


    for i in range(100):
        if list_aver[i][1] != 0:
            print("Интервал : ", i+1, "Количество : ", list_aver[i][1])
        else:
            print("Интервал : ", i+1, "Количество : ", list_aver[i][1])


cordX, cordY = pyautogui.position()
m = 2 ** 48
number0 = cordX
a = int(str(cordX) + str(cordY))
b = int(str(cordY) + str(cordX))
a = check_a(a)
b = check_b(b)
print('Коэффициент а = ', a)
print('Коэффициент b = ', b)
print('Начальное число = ', number0)
print(NOD(m, b))


def sequence_number(len_seque, file_name, number):
    string = ""
    while True:
        number = generate_random_number(number)
        string += str(number)
        if len(string) >= len_seque:
            with open(file_name, "w", encoding='utf-8') as file_write:
                file_write.write(string[0:len_seque])
            break


def coder(file_name, key):
    with open(file_name, "r", encoding='utf-8') as f:
        original_text = f.readlines()

    length = 0
    text = ""
    for st in original_text:
        text += st
        length += len(st)
    print(text)
    sequence_number(length, key, number0)
    with open(key, "r", encoding='utf-8') as k:
        mask = k.readline()
    print(mask)

    result = ""
    for i in range(length):
        result += chr(ord(text[i]) ^ int(mask[i]))

    with open("coder.txt", "w", encoding='utf-8') as f:
        f.write(result)


def decoder(file_name, key):
    with open(file_name, "r", encoding='utf-8') as f:
        text = f.readlines()

    code_text = ""
    for i in text:
        code_text += i

    with open(key, "r", encoding='utf-8') as k:
        mask = k.readline()

    decode_text = ""
    for i in range(len(mask)):
        decode_text += chr(ord(code_text[i]) ^ int(mask[i]))

    with open("decoder.txt", "w", encoding='utf-8') as f:
        f.write(decode_text)


print(distribution())
'''
def choose_file():
    filetypes = (("Текстовый файл", "*.txt"),
                 ("Изображение", "*.jpg *.gif *.png"),
                 ("Любой", "*"))
    filename = fd.askopenfilename(title="Открыть файл", initialdir="/Lab2",
                                  filetypes=filetypes)
    fn.insert(0, filename)


def encrypt():
    global Text
    Text.destroy()
    text1 = ''
    file_name = fn.get()
    coder(file_name, "mask.txt")
    with open("coder.txt", "r", encoding='utf-8') as f:
        for i in f:
            text1 += i
    Text = tk.Label(window, text=f'{text1}', wraplength=350, justify='left')
    Text.place(x=30, y=120)
    fn.delete(0, tk.END)


def decrypt():
    global Text
    Text.destroy()
    text1 = ''
    file_name = fn.get()
    decoder(file_name, "mask.txt")
    with open("decoder.txt", "r", encoding='utf-8') as f:
        for i in f:
            text1 += i
    Text = tk.Label(window, text=f'{text1}', wraplength=350, justify='left')
    Text.place(x=30, y=120)
    fn.delete(0, tk.END)


def one_number():
    num = pole.get()
    number = generate_random_number(int(num))
    pole.delete(0, tk.END)
    pole.insert(0, number)


def seque():
    sequence_number(100, "key.txt", number0)
    Text = tk.Label(window, text='Последовательность записана в файл key.txt', wraplength=200, justify='left')
    Text.place(x=400, y=120)
    


window = tk.Tk()
window.geometry('700x700')
Text = tk.Label(window, text='', wraplength=600, justify='left')
Text.place(x=30, y=120)

fn = tk.Entry(width=30)
fn.place(x=30, y=20)

pole = tk.Entry(width=30)
pole.insert(0, number0)
pole.place(x=400, y=20)

btn1 = tk.Button(text='Зашифровать', background='#c5edd2', command=encrypt)
btn1.place(x=30, y=50)

btn2 = tk.Button(text='Расшифровать', background='#c5edd2', command=decrypt)
btn2.place(x=150, y=50)

btn_file = tk.Button(window, text="Обзор", command=choose_file)
btn_file.place(x=280, y=17)

btn3 = tk.Button(text='Число', background='#c5edd2', command=one_number)
btn3.place(x=400, y=50)

btn4 = tk.Button(text='Последовательность', background='#c5edd2', command=seque)
btn4.place(x=480, y=50)
window.mainloop()'''
