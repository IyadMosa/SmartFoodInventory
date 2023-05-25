import { RestRequest } from "./RestRequest";
import {
  LOGIN_ERROR,
  LOGIN_SUCCESS,
  REGISTER_ERROR,
  REGISTER_SUCCESS,
} from "./types";

export const login = (auth, history) => (dispatch, getState) =>
  RestRequest(
    "/api/auth/login",
    "POST",
    auth,
    "login success"
  )(dispatch, getState)
    .then((data) => {
      // Save the token in local storage or a cookie
      const token = data.token;
      localStorage.setItem("token", token);
      dispatch({ type: LOGIN_SUCCESS, payload: token });
      history.push("/my-store"); // Redirect to the dashboard page
    })
    .catch((error) => {
      console.log(error);
      dispatch({ type: LOGIN_ERROR, payload: error.message });
    });

export const register = (user) => (dispatch, getState) =>
  RestRequest(
    "/api/auth/register",
    "POST",
    user,
    "register success"
  )(dispatch, getState)
    .then(() => {
      dispatch({ type: REGISTER_SUCCESS });
    })
    .catch((error) => {
      dispatch({ type: REGISTER_ERROR, payload: error.message });
    });
