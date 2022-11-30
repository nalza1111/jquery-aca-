/**
 * makeUl.js <= ul.js
 */
 import Ul from './ul.js';
 
 let fruits = ["사과", "복숭아", "포도"];
 
 let app = document.getElementById('app');
 let list = new Ul(fruits); //<ul><li>사과</li><li>복숭아</li>...</ul>
 app.append(list);