import {
  GET_USER,
  LOGIN_ERROR,
  LOGIN_SUCCESS,
  REGISTER_ERROR,
  REGISTER_SUCCESS,
} from "../actions/types";

const initialState = {
  token: localStorage.getItem("token"),
  user: {},
  error: null,
};

export default function authReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return {
        ...state,
        token: action.payload,
        error: null,
      };
    case LOGIN_ERROR:
      return {
        ...state,
        token: null,
        error: action.payload,
      };
    case REGISTER_SUCCESS:
      return {
        ...state,
        error: null,
      };
    case REGISTER_ERROR:
      return {
        ...state,
        error: action.payload,
      };
    case GET_USER:
      return {
        ...state,
        user: action.payload,
      };
    default:
      return state;
  }
}
