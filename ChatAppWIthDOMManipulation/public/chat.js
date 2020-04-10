( function IIFE() {
  'use strict';
  const loginButton = document.querySelector(".login button");
  const username = document.querySelector(".curr-user");
  const sendButton = document.querySelector(".send button");
  const toSend = document.querySelector(".to-send");
  const userList = document.querySelectorAll("ul > li div.user span.username");
  const messageUserList = document.querySelectorAll("ol > li div.message div.meta-info div.sender-info span.username");
  const ul = document.querySelector("ul");
  const ol = document.querySelectorAll("ol > li");

  ol.forEach((ele) => {
    const newEle = document.createElement('p');
    newEle.className = "hiddenMessage";
    newEle.innerHTML = "<:--------------------:>";
    ele.appendChild(newEle);
  });
  
  messageUserList.forEach((chat) => {
      const message = chat.parentNode.parentNode.parentNode;
      const child = message.parentNode.children;
      child[1].style.display = "none";
    });

  let selectedUsers = {};

  if(username && loginButton) {
    loginButton.disabled = !username.value;
    username.addEventListener('input', (e) => {
      if(e.target.value) {
        loginButton.disabled = false;
        const cUser = e.target.value;
      } else {
        loginButton.disabled = true;
      }
    });
  }

  if(toSend && sendButton) {
    sendButton.disabled = !toSend.value;
    toSend.addEventListener('input', (e) => {
      if(e.target.value) {
        sendButton.disabled = false;
      } else {
        sendButton.disabled = true;
      }
    });
  }
   
  if(ul)
  {
    const unselectButton = document.createElement('Button');
    unselectButton.innerHTML = "Unselect All";
    const uButton = ul.appendChild(unselectButton);
    uButton.style.visibility = "hidden";

    userList.forEach((ele) => {
      ele.addEventListener('click', (e) =>{
        if(ele.innerHTML in selectedUsers)
        {
          delete selectedUsers[ele.innerHTML];

          if(Object.keys(selectedUsers).length === 0)
          {
            messageDisplay();
            changeUserColor();
          }
        }
        else
        {
          selectedUsers[ele.innerHTML] = ele.innerHTML;
        }
        if(Object.keys(selectedUsers).length !== 0)
        {
          uButton.style.visibility = "visible";
        }
        else
        {
          uButton.style.visibility = "hidden";
        }
        changeUserColor();
        messageDisplay();
      });
    });

    function changeUserColor()
    {
      userList.forEach((ele) => {
        if(ele.innerHTML in selectedUsers)
        {
          ele.style.color = 'red';
        }
        else
        {
          ele.style.color = 'black';
        }
      });
    }
    
    function messageDisplay(){
      let flag = " ";
      messageUserList.forEach((chat) => {
        const message = chat.parentNode.parentNode.parentNode;
        const child = message.parentNode.children;
        if((chat.innerHTML in selectedUsers) || (Object.keys(selectedUsers).length === 0))
        {
          flag = chat.innerHTML;
          if(message.style.display === "none")
          {
            message.style.display = "block";
            child[1].style.display = "none";
          }
          else
          {
            message.style.visibility = "visible";
            child[1].style.display = "none";
          }
        }
        else
        {
          if(flag === chat.innerHTML)
          {
            message.style.display = "none";
            child[1].style.display === "none";
          }
          else
          {
            flag = chat.innerHTML;
            message.style.display = "none";
            if(child[1].style.display === "none")
            {
              child[1].style.display = "block";
            }
            else
            {
            child[1].style.visibility = "visible";
            }
          }
        }
      });
    }

    uButton.addEventListener('click', (e) => {
      uButton.style.visibility = "hidden";
      selectedUsers = {};
      messageDisplay();
      changeUserColor();
    });
  }

})();
