'use client';

import { useState } from 'react';
import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { useUsers } from '@/features/users/hooks/useUsers';
import { UserCard } from '@/features/users/components/UserCard';
import { UserForm } from '@/features/users/components/UserCard';
import { Button } from '@/components/ui/button';
import { Modal } from '@/components/ui/modal';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { User } from '@/core/types';
import { Plus } from 'lucide-react';

export default function UsersPage() {
  const { users, loading, error, createUser, updateUser, deleteUser } = useUsers();
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [editingUser, setEditingUser] = useState<User | null>(null);
  const { showToast, ToastComponent } = useToast();

  const handleCreate = async (userData: Omit<User, 'id'>) => {
    try {
      await createUser(userData);
      setIsCreateModalOpen(false);
      showToast({ message: 'User created successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to create user', type: 'error' });
    }
  };

  const handleUpdate = async (userData: Partial<User>) => {
    if (!editingUser) return;
    try {
      await updateUser(editingUser.id, userData);
      setEditingUser(null);
      showToast({ message: 'User updated successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to update user', type: 'error' });
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteUser(id);
      showToast({ message: 'User deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete user', type: 'error' });
    }
  };

  if (loading && users.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-slate-900">Users</h1>
            <p className="text-slate-500 mt-1">Manage user accounts</p>
          </div>
          <Button onClick={() => setIsCreateModalOpen(true)}>
            <Plus className="h-4 w-4 mr-2" />
            Add User
          </Button>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {users.map((user) => (
            <UserCard
              key={user.id}
              user={user}
              onEdit={setEditingUser}
              onDelete={handleDelete}
            />
          ))}
        </div>

        {users.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No users found. Create one to get started.</p>
          </div>
        )}
      </div>

      {/* Create Modal */}
      <Modal
        isOpen={isCreateModalOpen}
        onClose={() => setIsCreateModalOpen(false)}
        title="Create New User"
        size="md"
      >
        <UserForm
          onSubmit={handleCreate}
          onCancel={() => setIsCreateModalOpen(false)}
        />
      </Modal>

      {/* Edit Modal */}
      <Modal
        isOpen={!!editingUser}
        onClose={() => setEditingUser(null)}
        title="Edit User"
        size="md"
      >
        {editingUser && (
          <UserForm
            user={editingUser}
            onSubmit={handleUpdate}
            onCancel={() => setEditingUser(null)}
          />
        )}
      </Modal>
    </DashboardLayout>
  );
}
