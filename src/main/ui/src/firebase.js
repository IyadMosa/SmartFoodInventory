import React, { useEffect } from "react";
import firebase from "firebase/app";
import "firebase/messaging";

const FirebaseMessaging = () => {
  useEffect(() => {
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

    if ("serviceWorker" in navigator) {
      navigator.serviceWorker
        .register("/firebase-messaging-sw.js")
        .then((registration) => {
          messaging.useServiceWorker(registration);
        })
        .catch((error) => {
          console.error("Error registering service worker:", error);
        });
    } else {
      console.log("Service workers are not supported.");
    }

    // Request notification permission and retrieve device token
    messaging
      .requestPermission()
      .then(() => {
        console.log("Notification permission granted.");

        messaging
          .getToken()
          .then((currentToken) => {
            if (currentToken) {
              localStorage.setItem("device-token", currentToken);
            } else {
              console.log("No device token available.");
            }
          })
          .catch((error) => {
            console.error("Error retrieving device token:", error);
          });
      })
      .catch((error) => {
        console.error("Error requesting notification permission:", error);
      });
  }, []);

  return <div>Initializing Firebase...</div>;
};

export default FirebaseMessaging;
