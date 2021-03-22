import React, { Fragment } from 'react';

import {
  Button,
  Modal,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  HStack,
  useDisclosure,
} from '@chakra-ui/react';
import axios from 'axios';

export default function DeleteComment({ comment, fetchComments }) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  const url = `http://localhost:8080/api/forms/${comment.form.id}/comments/${comment.id}`;

  const handleDelete = async () => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    try {
      await axios.delete(url, config);
      fetchComments();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Fragment>
      <Button size="xs" onClick={onOpen} colorScheme="blue">
        Delete
      </Button>
      <Modal onClose={onClose} isOpen={isOpen} isCentered>
        <ModalOverlay>
          <ModalContent>
            <ModalHeader>Delete Comment</ModalHeader>
            <ModalCloseButton />
            <ModalFooter>
              <HStack spacing={4}>
                <Button onClick={onClose}>Close</Button>
                <Button onClick={handleDelete} colorScheme="blue">
                  Delete
                </Button>
              </HStack>
            </ModalFooter>
          </ModalContent>
        </ModalOverlay>
      </Modal>
    </Fragment>
  );
}
