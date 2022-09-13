import './App.css';

import Navbar from "./components/Navbar";
import ProjectList from "./components/ProjectList/ProjectList";
import React, {useState} from "react";
import {OpenAPIProvider, useOperation} from "react-openapi-client";

const App = () => {

    const {loading, data, error} = useOperation("getProjects");


  return (
      <OpenAPIProvider definition="http://http://localhost:8180/api/findev/v3/api-docs">
          <Navbar/>
          <ProjectList items = {data}/>
      </OpenAPIProvider>
  );
}

export default App;
