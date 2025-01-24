import {ProjectSo, UserSo} from '../../openapi';
import ProjectItem from './ProjectItem';
import React, {useEffect, useState} from "react";
import NewProjectForm from "../NewProject/NewProjectForm";

interface Props {
    projects: ProjectSo[],
}

const ProjectList = (props: Props) => {

    return (
        <div>
            <div className='d-flex justify-content-around flex-wrap'>
                {props.projects.map((project) => (
                    <ProjectItem
                        key={project.id}
                        {...project}
                    ></ProjectItem>
                ))}
            </div>

        </div>
    );
};

export default ProjectList;
