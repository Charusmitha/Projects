const chatPageURL = "http://localhost:3000";
const chatWeb = {
  chatPage: function(chat, cuser) {
    return `
      <!DOCTYPE html>
      <html>
        <head>
          <link rel="stylesheet" href="/chat.css"/>
          <title>Chat</title>
        </head>
        <body>
          <div id="chat-app">
            <div class="logout">
               ${chatWeb.getRefresh(cuser)}
               ${chatWeb.getLogout(cuser)}
            </div>
            <div class="display-panel">
              ${chatWeb.getUserList(chat)}
              ${chatWeb.getMessageList(chat)}
            </div>
            ${chatWeb.getOutgoing(cuser)}
          </div>
        </body>
      </html>
  `;
  },

  getMessageList: function(chat) {
    if (!Array.isArray(chat.messages) || !chat.messages.length)
    {
    return `
      <ol class="no-messages"> 
        <li>No messages to display</li>
      </ol>
      `;
    }
    else
    {
    return `<ol class="messages">` +
      chat.messages.map( message => `
        <li>
          <div class="message">
            <div class="meta-info">
              <div class="sender-info">
                <span class="username">${message.sender}</span>
              </div>
              <div class="message-info">
                <span class="timestamp">${message.timestamp}</span>
              </div>
            </div>
            <p class="message-text">${message.text}</p>
          </div>
        </li>
      `).join('') +
      `</ol>`;
    }
  },

  getUserList: function(chat) {
    return `<ul class="users">` +
    Object.values(chat.users).map( user => `
      <li>
        <div class="user">
          <span class="username">${user}</span>
        </div>
      </li>
    `).join('') +
    `</ul>`;
  },

  getOutgoing: function(cuser) {
    return `
      <div class="outgoing">
        <form action="/chat" method="POST">
          <input class="to-send" name="text" value="" placeholder="Enter message to send"/>
          <input type="hidden" id="loggedIn" value=${cuser}  name="username" />
          <button class="send-button" type="submit">Send</button>
        </form>
      </div>
    `;
  },

  getRefresh: function(cuser){
    return `
      <div class="refresh">
        <form action="${chatPageURL}", method="GET">
        <input type="hidden" id="current-user" value=${cuser}  name="curr_user" />
          <button class="refresh-button" type="submit">Refresh</button>
        </form>
      </div>
    `;
  },

  getLogout: function(cuser) {
    return `
      <div class="logout">
        <form action="/logout?curr_user=${cuser}" method="POST">
          <button class="logout-button" type="submit">Log Out</button>
        </form>
      </div>
    `;
  }
};
module.exports = chatWeb;
