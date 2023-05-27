import React from "react";
import { LoginScreen } from "@iyadmosa/react-library";
import { login, register } from "../actions/authAction";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";

const LoginPage = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  const loginErrorMsg = useSelector((state) => state.auth.error);
  return (
    <div>
      <LoginScreen
        onLogin={(auth) => dispatch(login(auth, history))}
        onRegister={(user) => dispatch(register(user))}
        imgPath="https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1074&q=80"
        errorMsg={loginErrorMsg}
      />
      {loginErrorMsg & <p>${loginErrorMsg}</p>}
    </div>
  );
};

export default LoginPage;
