import {useForm} from "react-hook-form";
import {Button, FloatingLabel, Form} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import useApiHook from "../../hooks/useApiHook";
import {UserSo} from "../../openapi";

type ProjectDataInput = {
    title: string;
    description: string;
}

type Props = {
    users: UserSo[];
    handleUpdate: () => void;
};

const NewProjectForm = (props: Props) => {
    const api = useApiHook();

    const { register, handleSubmit } = useForm({
        shouldUseNativeValidation: true
    });

    const onSubmit = async (data: ProjectDataInput) => {
        api.createProject(userId!, data).then(() => {props.handleUpdate();});

    }

    const [userId, setUserId] = useState<number>(null);

    return (
        <div className="w-100 d-inline-block">
            <form onSubmit={handleSubmit(onSubmit)}>
                <FloatingLabel
                    controlId="floatingInput"
                    label="Title"
                    className="mb-3"
                >
                    <Form.Control type="text" {...register("title", { required: "Please enter title." })} />
                </FloatingLabel>
                <FloatingLabel controlId="floatingInput" label="Description">
                    <Form.Control className="mb-3" as="textarea" rows={3} {...register("description", { required: "Please enter description." })}/>
                </FloatingLabel>
                <Form.Select value={userId} onChange={event => setUserId((event.target.value as unknown) as number)}>
                    <option key = 'default' value = ""> Select user </option>
                    {props.users.map((user) => (
                        <option key={user.id} value={user.id}>{user.id} {user.name} {user.surname}</option>
                    ))}
                </Form.Select>
                <Button className="m-4 " as="input" type="submit" value="Create Project" />{' '}
            </form>
        </div>
    )
}

export default NewProjectForm;