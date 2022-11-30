/**
 * 
 */

document.addEventListener('DOMContentLoaded',xmlCreate);

async function xmlCreate(){ //async await-->동기방식으로 실행됨
    console.log('1');
    let doc;
    await fetch('./cd_catalog.xml')
    .then(result => {return result.text()})
    .then(result => {
        console.log('2');
        // text포맷을 xml포맷으로 변경.
        const xmlStr = result;
        const parser = new DOMParser();
        doc = parser.parseFromString(xmlStr,"application/xml");
        console.log(doc);
       // let data = doc.getElementByTagName('CD');
       // console.log(data);
    })
    .catch(error => console.log(error));
    console.log('3'); //1->3->2 펫치결과를 기다리지않고 3번실행(비동기)
   
    let tbody = document.createElement('tbody');
    //데이터 건수.
    let data = doc.getElementsByTagName('CD');//HTMLCollection[CD,CD...]

    for(let cd of data){
        let tr = document.createElement('tr');
        let items = cd.children;
        for (let prop of items){
            let td = document.createElement('td');
            td.innerText = prop.textContent; //<TITLE>/</TITLE>
            tr.append(td);
        }
        document.getElementById('show').append(tr);
    }

    //thead생성
    let thead = document.createElement('thead');
    let tr = document.createElement('tr');
    let titles =doc.getElementsByTagName('CD')[0].children;
    for(let title of titles){
        console.log(title, title.nodeName);
        let th = document.createElement('th');
        th.innerText = title.nodeName;
        tr.append(th);
    }
    thead.append(tr);

    //table 구성
    let table = document.createElement('table');
    table.setAttribute('border','1')
    table.prepend(thead);
    table.append(tbody);
    document.getElementById('show').append(table);
   	console.log(data); //동기방식으로 순서대로 처리되기때문에 이게 덴구문밖에 있어도 실행됨
}