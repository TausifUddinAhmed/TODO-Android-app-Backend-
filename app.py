from flask import Flask, render_template, request, make_response
from flask import jsonify
import pymysql
import jwt 
import datetime
from functools import wraps
import json
from passlib.hash import sha256_crypt

app = Flask(__name__)
host = "127.0.0.1"
user = "root"
password = ""
db = "todo"

# conn = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
# cursor = conn.cursor()

@app.route('/')
def index():
	return 'Hello, Flask!'

@app.route('/signup', methods=['POST'])                
def signup():
    conn = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
    cursor2 = conn.cursor()
    try:
        if request.method =='POST':
            username =  request.form['username']
            print (username)
            _apassword = request.form['password']
            encrypt_password = sha256_crypt.encrypt("_apassword")
            email =request.form['email']
            _isActive = 1   
           
            cursor2.execute("SELECT Email FROM user")
            users = cursor2.fetchall()

            user_data = json.dumps(users)
            user_data2 = json.loads(user_data)
            print (user_data2)
           
            for usersEmail in user_data2:

                if usersEmail["Email"] == email:
                    res={"response": "Email Already exists"}
                    return jsonify(res)     
            
            sql = "INSERT INTO user(UserName,Email,PasswordHash,IsActive) VALUES(%s,%s,%s,%s)"
            cursor2.execute(sql,(username,email,_apassword,_isActive))
            conn.commit()
            cursor2.close()
            conn.close()
            res={"response": "success"}
            return jsonify(res)
    except:
        res={"response": "failed"}
        return jsonify(res)

@app.route('/login', methods=['POST'])                     
def login():
    conn = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
    cursor3 = conn.cursor()
    try:
        if request.method =='POST':

            print (request.form['email'])
            print (request.form['password'])

            a_email = request.form['email']
            a_password = request.form['password']

               
                
            cursor3.execute("SELECT Email,PasswordHash FROM user")
            users = cursor3.fetchall()

            user_data = json.dumps(users)
            user_data2 = json.loads(user_data)
            print (user_data2)

           
            for userall in user_data2:

                if userall["Email"] == a_email and userall["PasswordHash"] == a_password:
                    res={"response": "success"}
                    return jsonify(res)  
    
            else:
                res={"response": "Wrong Credentials"}
                return jsonify(res)
            cursor3.close()
            conn.close()
    except:
        res={"response": "failed"}
        return jsonify(res)       


if __name__ == '__main__':
    app.run(debug=True,host= '0.0.0.0', port='5000')