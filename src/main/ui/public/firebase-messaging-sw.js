importScripts(
  "https://www.gstatic.com/firebasejs/9.1.2/firebase-app-compat.js"
);
importScripts(
  "https://www.gstatic.com/firebasejs/9.1.2/firebase-messaging-compat.js"
);

const firebaseConfig = {
  apiKey: "AIzaSyC7g_O4maaaW43t91IcIGZinFmRrewkQ00",
  authDomain: "smartfoodinventory.firebaseapp.com",
  projectId: "smartfoodinventory",
  storageBucket: "smartfoodinventory.appspot.com",
  messagingSenderId: "639940430251",
  appId: "1:639940430251:web:4454c85276438255caeaee",
  measurementId: "G-PWCQW0P47K",
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
