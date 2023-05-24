import React, { createContext, useState } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [jwtToken, setJwtToken] = useState("");

  const handleLogin = (token) => {
    setAuthenticated(true);
    setJwtToken(token);
  };

  const handleLogout = () => {
    setAuthenticated(false);
    setJwtToken("");
  };

  return (
    <AuthContext.Provider
      value={{ authenticated, jwtToken, handleLogin, handleLogout }}
    >
      {children}
    </AuthContext.Provider>
  );
};
