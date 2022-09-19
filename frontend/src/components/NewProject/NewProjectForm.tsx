import {ApplicationApi, Configuration, UserSo} from "../../openapi";
import {useForm} from "react-hook-form";
import {Button, FloatingLabel, Form} from "react-bootstrap";
import React, {useState} from "react";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

type ProjectDataInput = {
    title: string;
    description: string;
}

interface UserProps {
    user: UserSo[];
}

const NewProjectForm = (props: UserProps) => {
    const { register, handleSubmit } = useForm({
        shouldUseNativeValidation: true
    });

    const onSubmit = async (data: ProjectDataInput) => {
        api.createProject(userId!, data).then();
        console.log("CREATE PROJECT");
    }

    const [userId, setUserId] = useState<number>();

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
                    <Form.Control className="mb-3" type="text" {...register("description", { required: "Please enter description." })}/>
                </FloatingLabel>
                <Form.Select value={userId} onChange={event => setUserId((event.target.value as unknown) as number)}>
                    {props.user.map((user) => (
                        <option key={user.id} value={user.id}>{user.id} {user.name} {user.surname}</option>
                    ))}
                </Form.Select>
                <Button className="m-4 " as="input" type="submit" value="Create Project" />{' '}
            </form>
        </div>
    )
}

export default NewProjectForm;