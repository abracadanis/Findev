import { ProjectSo } from '../../openapi';
import ProjectItem from './ProjectItem';
import {createBrowserRouter} from "react-router-dom";
import React from "react";

interface ProjectProps {
    project: ProjectSo[];
}

const ProjectList = (props: ProjectProps) => {

    return (
        <div className='d-flex justify-content-around flex-wrap'>
            {props.project.map((project) => (
                <ProjectItem
                    key={project.title}
                    {...project}
                ></ProjectItem>
            ))}
        </div>
    );
};

export default ProjectList;
