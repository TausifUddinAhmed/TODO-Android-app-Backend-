from flask import Flask, render_template, request, make_response
from flask import jsonify
import pymysql
import jwt 
import datetime
from functools import wraps
import json

app = Flask(__name__)
host = "127.0.0.1"
user = "root"
password = ""
db = "todo"

conn = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
cursor = conn.cursor()

@app.route('/')
def index():
	return 'Hello, Flask!'



if __name__ == '__main__':
    app.run(debug=True,host= '0.0.0.0', port='5000')