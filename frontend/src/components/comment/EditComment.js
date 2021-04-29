import React, { Fragment, useState } from 'react';

import {
  Button,
  FormControl,
  Textarea,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  HStack,
  useDisclosure,
} from '@chakra-ui/react';
import axios from 'axios';

export default function EditComment({ comment, fetchComments }) {
  const [text, setText] = useState('');
  const { isOpen, onOpen, onClose } = useDisclosure();
  const url = `http://localhost:8080/api/v1/forms/${comment.form.id}/comments/${comment.id}`;

  const handleSubmit = async () => {
    const data = {
      text,
    };
    const config = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    try {
      await axios.put(url, data, config);
      fetchComments();
    } catch (error) {
      console.error(error);
    }
    onClose();
    setText('');
  };

  return (
    <Fragment>
      <Button size="xs" onClick={onOpen} colorScheme="blue">
        Edit
      </Button>
      <Modal onClose={onClose} isOpen={isOpen} isCentered>
        <ModalOverlay>
          <ModalContent>
            <ModalHeader>Edit Comment</ModalHeader>
            <ModalCloseButton />
            <ModalBody>
              <FormControl id="text">
                <Textarea
                  type="post-title"
                  value={text}
                  onChange={(e) => setText(e.target.value)}
                />
              </FormControl>
            </ModalBody>
            <ModalFooter>
              <HStack spacing={4}>
                <Button onClick={onClose}>Close</Button>
                <Button
                  onClick={handleSubmit}
                  colorScheme="blue"
                  disabled={!text.trim()}
                >
                  Save
                </Button>
              </HStack>
            </ModalFooter>
          </ModalContent>
        </ModalOverlay>
      </Modal>
    </Fragment>
  );
}
