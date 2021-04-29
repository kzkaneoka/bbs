import React, { Fragment, useState, useEffect } from 'react';

import { Heading, Text, Container, VStack } from '@chakra-ui/react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';

import Comment from '../comment/Comment';
import AddComment from '../comment/AddComment';
import { isUser } from '../../utils/checkUser';

export default function FormDetail() {
  const [form, setForm] = useState([]);
  const [comments, setComments] = useState([]);
  const location = useLocation();

  const fetchForm = async () => {
    const url = 'http://localhost:8080/api/v1' + location.pathname;
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    try {
      const response = await axios.get(url, config);
      setForm(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchComments = async () => {
    const url =
      'http://localhost:8080/api/v1' + location.pathname + '/comments';
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    try {
      const response = await axios.get(url, config);
      setComments(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchForm();
    fetchComments();
  }, []);

  return (
    <Fragment>
      <Heading as="h2">{form.title}</Heading>
      <Text align="center" fontSize="30px">
        {form.description}
      </Text>
      <Container maxW="md" centerContent p={8}>
        <VStack spacing={8} w="100%">
          {Object.keys(comments).map((key) => {
            return (
              <Comment
                key={key}
                comment={comments[key]}
                fetchComments={fetchComments}
              />
            );
          })}
          {isUser() && (
            <VStack spacing={8} w="100%" color="blue.600">
              <AddComment
                formId={location.pathname}
                fetchComments={fetchComments}
              />
            </VStack>
          )}
        </VStack>
      </Container>
      {!isUser() && (
        <Text align="center" fontSize="30px">
          Please sign up / log in and add comments
        </Text>
      )}
    </Fragment>
  );
}
