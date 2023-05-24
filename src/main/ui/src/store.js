import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

// Define any initial state if needed
const initialState = {};

// Define an array of middleware
const middleware = [thunk]; // Add any additional middleware here

// Create the Redux store
const store = createStore(
  rootReducer,
  initialState,
  compose(applyMiddleware(...middleware))
);

export default store;
