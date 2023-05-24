import React, { useEffect } from "react";
import Dashboard from "./Dashboard";
import { MainScreen } from "@iyadmosa/react-library";
import SharedStore from "./SharedStore";

const DashboardPage = () => {
  const brand = { name: "NavbarScroller", to: "/dashboard" };
  const links = [
    {
      name: "Store",
      to: "/dashboard",
      component: <Dashboard />,
    },
    {
      name: "Shared store",
      to: "/shared",
      component: <SharedStore />,
    },
  ];

  return <MainScreen brand={brand} links={links} />;
};

export default DashboardPage;
