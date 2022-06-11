#!/bin/sh

echo "Ansible Entrypoint"
echo "Adding password to inventory..."
echo "ansible_ssh_pass=$AWS_SSH_PWD" >> /inventory
echo "Running Playbook..."

ansible-playbook playbook.yml
