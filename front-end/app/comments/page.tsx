'use client';

import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { useComments } from '@/features/comments/hooks/useComments';
import { Card, CardContent, CardHeader } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { Trash2, Mail } from 'lucide-react';

export default function CommentsPage() {
  const { comments, loading, error, deleteComment } = useComments();
  const { showToast, ToastComponent } = useToast();

  const handleDelete = async (id: number) => {
    try {
      await deleteComment(id);
      showToast({ message: 'Comment deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete comment', type: 'error' });
    }
  };

  if (loading && comments.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-slate-900">Comments</h1>
          <p className="text-slate-500 mt-1">View and manage comments</p>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="space-y-4">
          {comments.map((comment) => (
            <Card key={comment.id}>
              <CardHeader className="pb-3">
                <div className="flex items-start justify-between">
                  <div>
                    <h3 className="font-medium text-slate-900">{comment.name}</h3>
                    <div className="flex items-center gap-2 text-sm text-slate-500 mt-1">
                      <Mail className="h-3 w-3" />
                      {comment.email}
                    </div>
                  </div>
                  <Button
                    variant="danger"
                    size="sm"
                    onClick={() => handleDelete(comment.id)}
                  >
                    <Trash2 className="h-4 w-4" />
                  </Button>
                </div>
              </CardHeader>
              <CardContent>
                <p className="text-sm text-slate-600">{comment.body}</p>
                <p className="text-xs text-slate-400 mt-2">Post ID: {comment.post.id}</p>
              </CardContent>
            </Card>
          ))}
        </div>

        {comments.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No comments found.</p>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
